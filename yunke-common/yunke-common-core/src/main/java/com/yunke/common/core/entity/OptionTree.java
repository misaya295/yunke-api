package com.yunke.common.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 构建 ElementUI 级联选择器数据
 * <a href="https://element.eleme.cn/#/zh-CN/component/cascader">级联选择器组件</a>
 *
 * @author chachae
 * @version v1.0
 * @date 2020/6/4 21:56
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OptionTree<T> implements Serializable {

  private static final long serialVersionUID = -6278719841122418840L;

  private Long value;

  private String label;

  private List<OptionTree<T>> children;

  public OptionTree(Long value, String label, List<OptionTree<T>> children) {
    this.value = value;
    this.label = label;
    this.children = children;
  }

  public OptionTree(Long value, String label) {
    this(value, label, null);
  }

}
