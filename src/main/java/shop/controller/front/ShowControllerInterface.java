package shop.controller.front;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.annotation.Nullable;
import shop.util.Pagination;
@RequestMapping("/")
public interface ShowControllerInterface {
    @RequestMapping("")
    public String home(Model mode)throws Exception;
    @RequestMapping("search")
    public String search(@Nullable String keyword, @Nullable String sort,@Nullable Integer page, Model mode)throws Exception;
    @RequestMapping("category")
    public String category(@Nullable Integer categoryID,@Nullable Integer categoryItemID,@Nullable Integer page,@Nullable String sort, Model mode)throws Exception;
    @RequestMapping("product")
    public String product(@Nullable int id, Model mode)throws Exception;
}
