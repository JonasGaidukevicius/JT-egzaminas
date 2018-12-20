import React from 'react';
import PropTypes from 'prop-types';
import ProductCardComponent from './ProductCardComponent';
//import MyProvider from '../App';
import axios from 'axios';

class ProductListContainer extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            products: '',
            loading: 'Loading products. Please wait...'
        };
    }

    componentDidMount() {
        axios.get('http://localhost:8080/products')
            .then((response) => {
                this.setState({ products: response.data });
                console.log("Koks atiduodamas produktų sąrašas?");
                console.log(this.state.products);
            })
            .catch((error) => {
                console.log(error);
            });
    }

    render() {
        if (this.state.products) {
            const productCards = this.state.products.map((product, index) => {
                //Čia patikrinu ar ten yra reikšmių
                if (product.productDetails == null) {    
                    return (
                        <ProductCardComponent
                            key={index}
                            id={product.id}
                            title={product.title}
                            image="null"
                            description="null"
                            price={product.price}
                            quantity={product.quantity}
                        />
                    );
                }

                return (
                    <ProductCardComponent
                        key={index}
                        id={product.id}
                        title={product.title}
                        image={product.productDetails.image}
                        description={product.productDetails.description}
                        price={product.price}
                        quantity={product.quantity}
                    />
                );
            });
            return (<div className="row">{productCards}</div>);
        }
        return this.state.loading;
    }
}

export default ProductListContainer;