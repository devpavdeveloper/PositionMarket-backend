package by.psu.reporting;

public class HtmlBuilder {
    public enum TABLE_TYPES{MAIN_TABLE, TOTALS_TABLE};
    private static String[] footerHeaders = {"Стоимость до скидки","Скидка","К оплате"};
    public static String createOrderTable(TABLE_TYPES tt){
        StringBuilder sb = new StringBuilder();
        /*switch(tt){
            case MAIN_TABLE:{
                Set<Product> orderedProducts = orderIssue.getProducts();
                sb.append("<tbody>");
                for(Product product:orderedProducts){
                    sb.append("<tr>");
                        sb.append("<td>");
                        sb.append(product.getAttraction().getTitle());
                        sb.append("</td");
                        sb.append("<td>");
                        sb.append(product.getTypePrice().getTitle());
                        sb.append("</td");
                        sb.append("<th>");
                        sb.append(product.getPrice());
                        sb.append("</th");
                    sb.append("</tr>");
                }
                sb.append("</tbody>");
                break;
            }
            case TOTALS_TABLE:{
                sb.append("<tfoot>");
                for(int x = 0;x<3;x++){
                    sb.append("<tr>");

                    switch(x){
                        case 0:{
                            sb.append("<th colspan=\"2\">");
                            sb.append(footerHeaders[x]);
                            sb.append("</th");
                            sb.append("<th style=\"background-color: #2E86C1; color: white;\"\">");
                            sb.append(6000);
                            sb.append("</th");
                            break;
                        }
                        case 1:{
                            sb.append("<th>");
                            sb.append(footerHeaders[x]);
                            sb.append("</th");
                            sb.append("<th>");
                            sb.append(orderIssue.getUser().getDiscount()+"%");
                            sb.append("</th");
                            sb.append("<th style=\"background-color: #E74C3C; color: white;\"\">");
                            sb.append(600);
                            sb.append("</th");
                            break;
                        }
                        case 2:{
                            sb.append("<th colspan=\"2\">");
                            sb.append(footerHeaders[x]);
                            sb.append("</th");
                            sb.append("<th style=\"background-color: #2ECC71; color: white;\"\">");
                            sb.append(5400);
                            sb.append("</th");

                            break;
                        }
                    }
                    sb.append("</tr>");
                }
                sb.append("</tfoot>");
                break;
            }
        }*/
        return sb.toString();
    }
}
