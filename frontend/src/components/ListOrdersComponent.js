import React, { Component } from 'react';
import Pagination from '@material-ui/lab/Pagination';
import axios from 'axios';

class ListOrdersComponent extends Component {
    
    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.state= {
            page: 0,
            orders: [],
            numberOfPages: 0
        }
    }

    componentDidMount = () => {
        this.getOrders(0);
    }

    getOrders = (pageNumber) => {
        console.log('calling get orders api');
        
        // call orders get api
        axios.get(`http://localhost:8080/api/orders?page=`+pageNumber+`&size=10`)
            .then((res) => {
                this.setState({ orders: res.data.content });
                this.setState({numberOfPages: res.data.totalPages});
        });
    };
    
    handleChange = (event, pageNumber) => {
        this.setState({page: pageNumber});
        this.getOrders(pageNumber-1);
    };

    render() {
        const { page, numberOfPages } = this.state;

         return (
            <div>
                <h2 className="text-center">Orders</h2>
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>NRIC</th>
                                <th>Region</th>
                                <th>Country</th>
                                <th>Item Type</th>
                                <th>Sales Channel</th>
                                <th>Order Priority</th>
                                <th>Order Date</th>
                                <th>Order ID</th>
                                <th>Ship Date</th>
                                <th>Units Sold</th>
                                <th>Unit Price</th>
                                <th>Unit Cost</th>
                                <th>Total Revenue</th>
                                <th>Total Cost</th>
                                <th>Total Profit</th>
                            </tr>
                        </thead>

                        <tbody>
                            {
                                this.state.orders.map(
                                    (order, index) =>
                                    <tr key={index}>
                                        <td>{order.nric}</td>
                                        <td>{order.region}</td>
                                        <td>{order.country}</td>
                                        <td>{order.itemType}</td>
                                        <td>{order.salesChannel}</td>
                                        <td>{order.orderPriority}</td>
                                        <td>{order.orderDate}</td>
                                        <td>{order.orderId}</td>
                                        <td>{order.shipDate}</td>
                                        <td>{order.unitsSold}</td>
                                        <td>{order.unitPrice}</td>
                                        <td>{order.unitCost}</td>
                                        <td>{order.totalRevenue}</td>
                                        <td>{order.totalCost}</td>
                                        <td>{order.totalProfit}</td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>

                    <Pagination count={numberOfPages} variant="outlined" page={page} onChange={this.handleChange} color="primary" />
                </div>
                    
            </div>  
        );
    }
}

export default ListOrdersComponent;