package com.example.ebookbackend.domain;


import com.example.ebookbackend.DTO.RankBookNumberDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.query.Param;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "order_table")
@SqlResultSetMapping(
        name="rankBookMapping",
        classes =
        @ConstructorResult(
                targetClass = RankBookNumberDTO.class,
                columns = {
                        @ColumnResult(name = "book_id", type = Integer.class),
                        @ColumnResult(name = "totalNumber", type = Integer.class),
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "avatar"),
                        @ColumnResult(name = "author"),
                        @ColumnResult(name = "rank", type = Integer.class)
                }
        )

)
@NamedNativeQuery(name = "rankBookQ", query = "select (rank() over (order by sum(ob.number) desc)) as `rank`, ob.book_id as book_id, sum(ob.number) as totalNumber, b.name as name," +
        "b.path as avatar, b.author as author from order_table ob" +
        " join order_user_table ou on ou.order_id = ob.oid join book_table b on b.id = ob.book_id " +
        "where ou.time between :startTime and :endTime group by ob.book_id order by totalNumber desc",
        resultSetMapping = "rankBookMapping",
        hints = {
                @QueryHint(name = "org.hibernate.annotations.QueryHints.PARAM_DEF_EXPLICIT", value = "true")
        }
)
public class OrderBook {
    @Id
    // 指定了主键的生成策略，自增策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    private int number;

    @Column(name = "prices")
    private int prices;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "oid", insertable = false, updatable = false)
    private OrderUser orderUser;

    @ManyToOne
    @JsonIgnoreProperties({"id", "detail", "author"})
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private BookDetail book;

    @Column(name = "oid")
    private int oid;

    @Column(name = "book_id")
    private int book_id;

}
