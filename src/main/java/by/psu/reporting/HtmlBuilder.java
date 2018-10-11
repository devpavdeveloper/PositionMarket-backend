package by.psu.reporting;

import java.util.Iterator;

public class HtmlBuilder {
   /* public static String createOrderTable(Order order){
        StringBuilder sb = new StringBuilder();
        sb.append("<tbody>");
        sb.append("<tr>");

        sb.append("<td>");
        sb.append(order.getFirstName() + " " + order.getLastName());
        sb.append("</td>");

        sb.append("<td>");
        sb.append(order.getCompany());
        sb.append("</td>");

        sb.append("<td>");
        sb.append(order.getAddress());
        sb.append("</td>");

        sb.append("<td>");
        sb.append(order.getPostcode());
        sb.append("</td>");

        sb.append("<td>");
        sb.append(order.getPhoneNumber());
        sb.append("</td>");

        sb.append("<td>");
        sb.append(order.getEmail());
        sb.append("</td>");

        sb.append("<td>");
        sb.append(order.getComments());
        sb.append("</td>");

        sb.append("<td>");
        Iterator<String> iter;
        for(iter = order.getAttractions().iterator(); iter.hasNext();){
            sb.append(iter.next());
            if(iter.hasNext()){
                sb.append("\n");
            }
        }

        sb.append("</td>");

        sb.append("<td>");
        sb.append(order.getTotal());
        sb.append("</td>");

        sb.append("</tr>");

        sb.append("<tr>");
        sb.append("<td colspan=\"8\" align=\"center\">");
        sb.append("Итого по заказу:");
        sb.append("</td>");

        sb.append("<td style=\"color: white; background-color: green; text-align: center\">");
        sb.append(order.getTotal());
        sb.append("</td>");

        sb.append("</tr>");

        sb.append("</tbody>");

        return sb.toString();
    }*/
}
