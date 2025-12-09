package com.backend_API.Yarah.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend_API.Yarah.seller.Seller;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
    List<Sales> findBySeller(Seller seller);
    
    List<Sales> findBySellerAndSaleDateBetween(Seller seller, LocalDateTime start, LocalDateTime end);
    
    @Query("SELECT COUNT(s) FROM Sales s WHERE s.seller = :seller AND s.saleDate BETWEEN :start AND :end")
    Long countSalesBySellerAndDateRange(@Param("seller") Seller seller, 
                                        @Param("start") LocalDateTime start, 
                                        @Param("end") LocalDateTime end);

    @Query("SELECT SUM(s.saleAmount) FROM Sales s WHERE s.seller = :seller AND s.saleDate BETWEEN :start AND :end")
    java.math.BigDecimal sumSalesBySellerAndDateRange(@Param("seller") Seller seller, 
                                                   @Param("start") LocalDateTime start, 
                                                   @Param("end") LocalDateTime end);
}