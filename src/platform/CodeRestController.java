package platform;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CodeRestController {

    private final CodeService codeService;

    public CodeRestController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping(value = "/api/code/{id}", produces = "application/json")
    public Code getApiCode(@PathVariable String id) {
        return codeService.getCode(id);
    }

    @GetMapping(value = "/api/code/latest", produces = "application/json")
    public List<Code> getLatest() {
        return codeService.getLatest();
    }

    @PostMapping(value = "/api/code/new", consumes = "application/json")
    public String createNewCode(@RequestBody Code inputCode) throws InterruptedException {

        return codeService.createCode(inputCode);
    }

}
