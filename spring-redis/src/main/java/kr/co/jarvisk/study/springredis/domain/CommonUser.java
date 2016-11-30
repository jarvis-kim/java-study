package kr.co.jarvisk.study.springredis.domain;

import lombok.*;

import java.io.Serializable;


@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonUser implements Serializable {

    private static final long serialVersionUID = -5385050998137070748L;

    private Long id;

    private String username;

    private String name;


}
