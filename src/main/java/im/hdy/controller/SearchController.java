package im.hdy.controller;

import im.hdy.utils.SearchUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hdy on 17/02/2018.
 */
@RestController
public class SearchController {

    @RequestMapping("/search")
    public String search(@RequestParam(required = true) String q, Integer page) {
        if (page == null) {
            page = 1;
        }
        String search = SearchUtils.search(10, q, page, 10);
        return search;
    }
}
