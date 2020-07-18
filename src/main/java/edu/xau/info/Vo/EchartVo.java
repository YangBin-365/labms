package edu.xau.info.Vo;

import edu.xau.info.bean.Series;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: 杨斌
 * @Date: 2020/7/15 0015 20:14
 */
@Data
@ToString
@AllArgsConstructor
public class EchartVo {
    public EchartVo() {
    }

    String[] legend;
    String[] xAxis ;
    Series[] series;
}
