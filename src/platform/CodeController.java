package platform;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class CodeController {
    private final CodeService codeService;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping("/code/{id}")
    public ModelAndView getCode(@PathVariable String id) {
        Map<String, Object> params = new HashMap<>();
        Code code = codeService.getCode(id);
        params.put("code", code);

        return new ModelAndView("showCodeById", params);
    }

    @GetMapping("/code/latest")
    public ModelAndView latest() {
        Map<String, Object> params = new HashMap<>();
        List<Code> latest = codeService.getLatest();
        params.put("latest", latest);
        return new ModelAndView("showLatest", params);
    }

    @GetMapping(value = "/code/new")
    public ModelAndView getCodeNew() {
        return new ModelAndView("showCodeNew");
    }
}
