package com.backend_API.Yarah.sales;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend_API.Yarah.seller.Seller;
import com.backend_API.Yarah.listing.Listing;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class SalesService {
    private final SalesRepository salesRepository;
    
    public Sales recordSale(Seller seller, Listing listing, BigDecimal saleAmount) {
        Sales sales = new Sales(seller, listing, saleAmount);
        return salesRepository.save(sales);
    }
    
    public List<Sales> getSellerSales(Seller seller) {
        return salesRepository.findBySeller(seller);
    }
    
    public Map<String, Object> getWeeklySalesStats(Seller seller) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime weekAgo = now.minus(7, ChronoUnit.DAYS);
        
        Long salesCount = salesRepository.countSalesBySellerAndDateRange(seller, weekAgo, now);
        BigDecimal revenue = salesRepository.sumSalesBySellerAndDateRange(seller, weekAgo, now);
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("salesCount", salesCount != null ? salesCount : 0L);
        stats.put("revenue", revenue != null ? revenue : BigDecimal.ZERO);
        stats.put("period", "week");
        
        return stats;
    }
    
    public Map<String, Object> getMonthlySalesStats(Seller seller) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime monthAgo = now.minus(30, ChronoUnit.DAYS);
        
        Long salesCount = salesRepository.countSalesBySellerAndDateRange(seller, monthAgo, now);
        BigDecimal revenue = salesRepository.sumSalesBySellerAndDateRange(seller, monthAgo, now);
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("salesCount", salesCount != null ? salesCount : 0L);
        stats.put("revenue", revenue != null ? revenue : BigDecimal.ZERO);
        stats.put("period", "month");
        
        return stats;
    }
    
    public List<Sales> getSalesByDateRange(Seller seller, LocalDateTime start, LocalDateTime end) {
        return salesRepository.findBySellerAndSaleDateBetween(seller, start, end);
    }
}