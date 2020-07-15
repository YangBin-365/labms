package edu.xau.info.Dto;

import edu.xau.info.bean.Series;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: 杨斌
 * @Date: 2020/7/15 0015 20:14
 */
@Data
@ToString
public class EchartDto {
    String[] legend;
    String[] xAxis ;
    Series[] series;
}
