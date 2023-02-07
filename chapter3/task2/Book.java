
package chapter3.task2;

import java.util.Date;

public record Book(
        String authorfirstname,
        String authorlastname,
        int isbn,
        String title,
        Date date,
        int pages) {
}