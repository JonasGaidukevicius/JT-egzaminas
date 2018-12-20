import React from 'react';
import {Component} from 'react';
import PropTypes from 'prop-types';
import OneProductComponent from './OneProductComponent';
import axios from 'axios';
import UserContext from '../../UserContext';
import AddToCartComponent from './AddTopCartComponent';

class OneProductContainer extends React.Component {
    constructor(props, context) {
        super(props, context);
        this.state = {
            id: 0,
            quantity: 0,
            quantityInStock: 0,
            title: "",
            //image: "",
            //description: "",
            price: 0,
            
        };
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! užkomentavau, nes man atrodo, kad čia aš eksperimentavau
        //this.userName = this.context;
        
    }
    
     handleAddToCart = (userName) => {      
        console.log("--------------vartotojo vardas yra " + userName);
        axios.post('http://localhost:8080/api/users/' + userName + '/cart-products', this.state)
        
            .then((response) => {
                console.log("Sėkminga" + response);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    handleChangeOfQuantity = (event) => {
        this.setState({ quantity: event.target.value });
        console.log(this.state.quantity);
      }



    componentDidMount() {
        const position = this.props.match.params.id;
        axios.get('http://localhost:8080/products/' + (position))
            .then((response) => {
                //this.setState(response.data);
                console.log("-----------------Response data id yra: " + response.data.id);
                console.log("-----------------Response data title yra: " + response.data.title);
                this.setState({id: response.data.id,
                                title: response.data.title,
                                price: response.data.price,
                                quantity: response.data.quantity,
                                quantityInStock: response.data.quantity})
                
            })
            .catch((error) => {
                console.log(error);
            });
    }

    render() {
        return (
            <div>
                <OneProductComponent title={this.state.title}
                                    description={this.state.description}
                                    price={this.state.price}
                                    //dabar taip
                                    quantity={this.state.quantityInStock}
                                    //buvo taip
                                    //------quantity={this.state.quantity}
                                    //handleAddToCart={this.handleAddToCart}
                                    //handleChangeOfQuantity={this.handleChangeOfQuantity}
                  />
                <UserContext.Consumer>
                {
                    (userNameObject) => {
                       return <AddToCartComponent userName={userNameObject.user} handleAddToCart={this.handleAddToCart} handleChangeOfQuantity={this.handleChangeOfQuantity}/>
                    }
                } 
                </UserContext.Consumer> 
            </div>
        );
    }
}

export default OneProductContainer;