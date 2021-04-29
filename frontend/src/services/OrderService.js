import axios from 'axios';

const ORDERS_BASE_URL = "http://localhost:8080/orders?page=0&size=3";
//orders = axios.get("http://localhost:8080/orders?page=0&size=3")
        
class OrderService{
    getOrders(){
        return axios.get(ORDERS_BASE_URL);
    }

}

export default new OrderService()