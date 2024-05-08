package com.example.ebookbackend.repo;

import com.example.ebookbackend.DTO.RankBookNumberDTOInterface;
import com.example.ebookbackend.DTO.RankUserConsumeDTOInterface;
import com.example.ebookbackend.DTO.StatisticsUserDTOInterface;
import com.example.ebookbackend.domain.OrderBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderBookRepository extends JpaRepository<OrderBook, Integer> {
    List<OrderBook> getOrderBooksByOid(int oid);

    @Query(nativeQuery = true, value = "select (rank() over (order by sum(ob.number) desc)) as `rank`, ob.book_id as book_id, sum(ob.number) as totalNumber, b.name as name," +
            "b.path as avatar, b.author as author from order_table ob" +
            " join order_user_table ou on ou.order_id = ob.oid join book_table b on b.id = ob.book_id " +
            "where ou.time between :startTime and :endTime group by ob.book_id order by totalNumber desc")
    List<RankBookNumberDTOInterface> getRankBookInfo(@Param("startTime") String startTime, @Param("endTime") String endTime);

    @Query(nativeQuery = true, value = "select (rank() over (order by sum(ot.number) desc)) as numberRank, (rank() over (order by sum(ot.prices) desc)) as moneyRank, u.id as uid, " +
            "u.username as username, sum(ot.number) as bookNumber, sum(ot.prices) as money from order_user_table ou join user_table u on ou.uid = u.id" +
            " join order_table ot on ot.oid = ou.order_id where ou.time between :startTime and :endTime " +
            "group by u.id")
    List<RankUserConsumeDTOInterface> getRankUserInfo(@Param("startTime") String startTime, @Param("endTime") String endTime);

    @Query(nativeQuery = true, value = "select sum(ot.number) as number, sum(ot.prices) as money, b.id as bookId, b.name as bookName, b.path as avatar, b.author as author" +
            " from order_user_table ou join order_table ot on ou.order_id = ot.oid " +
            " join book_table b on ot.book_id = b.id where ou.uid = :uid and (ou.time between :startTime and :endTime) " +
            " group by ot.book_id")
    List<StatisticsUserDTOInterface> getUserStat(@Param("uid") Integer uid, @Param("startTime") String startTime, @Param("endTime") String endTime);
}
