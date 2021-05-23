import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class ProductList extends Component {

    constructor(props) {
        super(props);
        this.state = { products: []};
    }

    componentDidMount() {
        fetch('/productprices?Quantity=50')
            .then(response => response.json())
            .then(data => this.setState({ products: data, isLoading: false }));
    }

    render() {
        const { products, isLoading } = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const productList = products.map( product => {
            return <tr key={product.quantity}>
                <td style={{whiteSpace: 'nowrap'}}>{product.quantity}</td>
                <td>{product.products.Horseshoe}</td>
                <td>{product.products.Penguinears}</td>
            </tr>
        });

        return (
            <div>
              <AppNavbar/>
              <Container fluid>
                <h3>Prodcut List With Price</h3>
                <Table className="mt-3">
                  <thead>
                  <tr>
                    <th width="20%">Quantity</th>
                    <th width="20%">Horseshoe</th>
                    <th width="20%">Penguin-ears</th>
                  </tr>
                  </thead>
                  <tbody>
                  {productList}
                  </tbody>
                </Table>
              </Container>
            </div>
          );
    }
}
export default ProductList;