package unicorn.converter;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PageableConverter {

    public static PagedListHolder convertToPageable(HttpServletRequest request, List list){
        PagedListHolder pagedListHolder = new PagedListHolder(list);
        int page = ServletRequestUtils.getIntParameter(request, "p", 0);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(10);
        return pagedListHolder;
    }
}
