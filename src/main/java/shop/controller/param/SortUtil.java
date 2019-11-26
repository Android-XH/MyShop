package shop.controller.param;

public class SortUtil {
    public static String handleSort(String sort){
        sort = sort==null?"":sort;
        String column = "";
        String order = "desc";
        switch(sort){
            case "date":
                column = "id";
                order = "asc";
                break;
            case "comment":
                //人气
                column = "tk_total_sales";
                break;
            case "volume":
                column = "volume";
                break;
            case "price":
                column = "price";
                order = "asc";
                break;
            case "priceInverse":
                column = "price";
                break;
            default:
                column = "category_item_id";
                break;
        }
        String res = String.format("%s %s ",column,order);
        return res;
    }
}
