package pos.app.pharmacy_app.stock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pos.app.pharmacy_app.stock.data.StockRequest;
import pos.app.pharmacy_app.stock.data.StockResponse;
import pos.app.pharmacy_app.stock.entity.Stocks;
import pos.app.pharmacy_app.stock.repository.StockRepository;
import pos.app.pharmacy_app.stock.service.StockService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("stocks")
public class StockController {
    StockService stockService;
    StockRepository repository;
    @PostMapping(value = "addStocks")
    public ResponseEntity<StockResponse>saveStock(@RequestBody StockRequest stockRequest){
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
    @GetMapping(value = "listStocks")
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
    @RequestMapping(value = "deleteSTock/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<StockResponse>deleteStocks(@PathVariable Long id){
        try{
    Optional<Stocks> stocks=repository.findById(id);
    StockResponse response=new StockResponse();
    if(stocks.isPresent()){
        stockService.deleteStock(id);
        response.setResponseCode("00");
        response.setMessage("Stock deleted");
        response.setProductCode(stocks.get().getProductsId().getProductCode());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }else {
        response.setMessage("Failed to delete. Product does not exist");
        response.setResponseCode("049");
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @RequestMapping(value = "updateStocks/{Id}")
    public ResponseEntity<StockResponse>updateStock(@RequestBody StockRequest request,@PathVariable Long Id){
         Optional<Stocks> stocks=repository.findById(Id);
         try{StockResponse response=new StockResponse();
             if (stocks.isPresent()){
                 stockService.updateStock(request, Id);
                  response.setResponseCode("00");
                  response.setMessage("Stocks details updated");
                  response.setSupplierName(request.getSuppliersId().getName());
                  response.setProductCode(request.getProductsId().getProductCode());
                 return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
             }else
             {
                 response.setResponseCode("049");
                 response.setMessage("update failed. The Id provided does not exist");
                 return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
             }
         }catch (Exception e){
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }
    @RequestMapping("showStocksById/{id}")
    public ResponseEntity<Stocks>showSockById(@PathVariable Long id){
        Optional<Stocks> stocks=repository.findById(id);
        try {
        if (stocks.isPresent()){
            Stocks stocks1=stockService.getStockById(id);
            return new ResponseEntity<>(stocks1,HttpStatus.OK);

        } else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
        catch (Exception e) {return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);}
    }


}
