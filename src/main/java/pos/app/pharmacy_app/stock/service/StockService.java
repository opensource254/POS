package pos.app.pharmacy_app.stock.service;

import org.springframework.stereotype.Service;
import pos.app.pharmacy_app.products.entity.Products;
import pos.app.pharmacy_app.products.repository.ProductRepository;
import pos.app.pharmacy_app.stock.data.StockRequest;
import pos.app.pharmacy_app.stock.data.StockResponse;
import pos.app.pharmacy_app.stock.entity.Stocks;
import pos.app.pharmacy_app.stock.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {
    StockRepository stockRepository;
    ProductRepository productRepository;

    public StockResponse addStock(StockRequest request){
        Stocks stocks=new Stocks();
        stocks.setQuantity(request.getQuantity());
        stocks.setProductsId(request.getProductsId());
        stocks.setPurchasePrice(request.getPurchasePrice());
        stocks.setSuppliersId(request.getSuppliersId());
        stocks.setSalePrice(request.getSalePrice());
        stocks.setEntryDate(request.getEntryDate());
        stocks.setExpiryDate(request.getExpiryDate());
        stockRepository.save(stocks);
        StockResponse response=new StockResponse();
        response.setResponseCode("00");
        response.setMessage("Stock added successfully");
        String products= productRepository.findById(request.getProductsId()).getProductCode();
        response.setProductCode(products);
        return response;
        }


        public void deleteStock(Long id){
        stockRepository.deleteById(id);
        }
        public List<Stocks> getALLStocks(){
            List<Stocks>stockList=new ArrayList<>();
            stockRepository.findAll().forEach(stocks -> stockList.add(stocks));
            return stockList;
        }
        public Stocks getStockById(Long id){
        return stockRepository.findById(id).get();
        }

        public StockResponse updateStock(StockRequest stockRequest,Long id){
        Stocks stocks=stockRepository.findById(id).get();
        stocks.setStockID(id);
        stocks.setPurchasePrice(stockRequest.getPurchasePrice());
        stocks.setSalePrice(stockRequest.getSalePrice());
        stocks.setEntryDate(stockRequest.getEntryDate());
        stocks.setExpiryDate(stockRequest.getExpiryDate());
        stocks.setSuppliersId(stockRequest.getSuppliersId());
        stocks.setQuantity(stockRequest.getQuantity());
        stocks.setProductsId(stockRequest.getProductsId());
        stockRepository.save(stocks);
        StockResponse response=new StockResponse();
        response.setProductCode("00");
        response.setMessage("Stock Upadted");
        response.setProductCode(response.getProductCode());
        return response;

    }

    }

