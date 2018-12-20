import React from 'react';
import NewProductComponent from './NewProductComponent';
import axios from 'axios';
import { withRouter } from 'react-router';


class NewProductContainer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      description: '',
      //id: '',
      image: '',
      price: 0,
      quantity: 0,
      title: ''
    };
    //var fromMenu;
  }

  handleChangeOfTitle = (event) => {
    this.setState({ title: event.target.value });
  }

  handleChangeOfImageUrl = (event) => {
    this.setState({ image: event.target.value });
  }

  handleChangeOfDescription = (event) => {
    this.setState({ description: event.target.value });
  }

  handleChangeOfPrice = (event) => {
    this.setState({ price: event.target.value });
  }

  handleChangeOfQuantity = (event) => {
    this.setState({ quantity: event.target.value });
  }

  handleSubmit = (event) => {
    event.preventDefault();
    console.log(this.state);
    axios.post('http://localhost:8080/products', this.state)
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  }

  componentDidMount() {

    //console.log(this.props.history.location.pathname);
  }


  render() {
    
    this.fromMenu = "Enter new product data:"

    return (
      <NewProductComponent handleChangeOfTitle={this.handleChangeOfTitle}
        handleChangeOfImageUrl={this.handleChangeOfImageUrl}
        handleChangeOfDescription={this.handleChangeOfDescription}
        handleChangeOfPrice={this.handleChangeOfPrice}
        handleChangeOfQuantity={this.handleChangeOfQuantity}
        handleSubmit={this.handleSubmit}
        fromMenu={this.fromMenu}
      />
    );
  }
}

export default withRouter(NewProductContainer);