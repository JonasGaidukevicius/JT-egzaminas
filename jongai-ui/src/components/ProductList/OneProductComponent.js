import React from 'react';
import PropTypes from 'prop-types';
import pic from './img/KingWear-KW06.jpg';
import { Link } from 'react-router-dom';
import UserContext from '../../UserContext';
// Stilius importuoju iš css failo
import './styles.css';

const OneProductComponent = (props) => {
    return (
        <div className="container">
            <div className="row">
                <div className="col-2">
                    <img src={pic} alt="Smartphone" width="150px"></img>
                </div>
                <div className="col-3">
                    <h5>{props.title}</h5>
                    <p>{props.description}</p>
                    <p>Price: {props.price} Eur.</p>
                    <p>Quantity in stock: {props.quantity} pcs.</p>
                </div>
            </div>
        </div>
    );
}

/*
OneProductComponent.propTypes = {
    id:PropTypes.number.isRequired,
    title: PropTypes.string.isRequired,
    image: PropTypes.string.isRequired,
    description: PropTypes.string.isRequired,
    price: PropTypes.number.isRequired,
    quantity: PropTypes.number.isRequired
};*/


export default OneProductComponent;