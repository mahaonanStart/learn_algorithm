package simulate.brave;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: M˚Haonan
 * @Date: 2020-01-08 18:28
 * @Description: 坐标点类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position {

    private int x;

    private int y;

    private String color;
}
