package platform;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class CodeService {
    private final CodeRepository codeRepository;

    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public Code getCode(String id) {
        Code code = codeRepository.findOneByUuid(UUID.fromString(id)).orElseThrow(CodeNotFoundException::new);
        System.out.println("get code " + code);
        long diffTime = code.getBaseTime() - Duration.between(code.getDate(), LocalDateTime.now()).toSeconds();
        if (code.getViews() == 0 && code.getTime() == 0 && code.getBaseTime() == 0 && code.getBaseViews() == 0) {
            return code;
        } else if (code.getViews() > 0 && code.getTime() == 0) {
            long updatedViews = code.getViews() - 1;
            code.setViews(updatedViews);
            return code;
        } else if (code.getTime() > 0 && code.getViews() == 0 && diffTime > 0) {
            code.setTime(diffTime);
            return code;
        } else if (code.getViews() == 0 || diffTime <= 0) {
            throw new CodeNotFoundException();
        } else {
            long updatedViews = code.getViews() - 1;
            code.setViews(updatedViews);
            code.setTime(diffTime);
            return code;
        }
    }

    public String createCode(Code inputCode) {
        System.out.println("create " + inputCode);
        Code code = new Code(inputCode.getCode(), LocalDateTime.now(), inputCode.getViews(), inputCode.getTime());
        code.setExtraId(Code.counter);
        codeRepository.save(code);
        Code.counter++;

        return new JSONObject()
                .put("id", String.valueOf(code.getUuid())).toString();
    }

    public List<Code> getLatest() {
        System.out.println("get latest");
        List<Code> latest = codeRepository.findAll().stream().filter(
                code -> code.getTime() == 0 && code.getViews() == 0
        ).sorted().collect(Collectors.toList());

        latest.forEach(System.out::println);
        if (latest.size() > 10) {
            return latest.subList(0, 10);
        } else {
            return latest;
        }
    }
}
