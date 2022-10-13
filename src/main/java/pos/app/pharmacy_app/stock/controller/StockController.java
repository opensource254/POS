package pos.app.pharmacy_app.stock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pos.app.pharmacy_app.stock.data.StockRequest;
import pos.app.pharmacy_app.stock.data.StockResponse;
import pos.app.pharmacy_app.stock.entity.Stocks;
import pos.app.pharmacy_app.stock.service.StockService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("stocks")
public class StockController {
    StockService stockService;
    @PostMapping(value = "addStocks")
    public ResponseEntity<StockResponse>saveStock(StockRequest stockRequest){
      try{
          StockResponse response= stockService.addStock(stockRequest);
          if (response.getResponseCode().equals(00)) {
              return new ResponseEntity<>(response,HttpStatus.CREATED);
          }else response.setResponseCode("049");
               response.setMessage("something went wrong. Failed to add stock");
               response.setProductCode(stockRequest.getProductsId().getProductCode());
               return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }catch (Exception e){
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    public ResponseEntity<List<Stocks>>getAllStock(){
        List<Stocks>stocksList;
        try{
            stocksList=stockService.getALLStocks();
            if (!stocksList.isEmpty()){
                return new ResponseEntity<>(stocksList,HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
