package vn.techmaster.todothymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ToDo {
    private int id;
    private String title;
    private boolean status;
}
