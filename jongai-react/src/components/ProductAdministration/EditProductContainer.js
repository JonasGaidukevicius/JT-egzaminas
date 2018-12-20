import React from 'react';
import EditProductComponent from './EditProductComponent';
import axios from 'axios';
import { withRouter } from 'react-router';

class EditProductContainer extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      id: 0,
      title: "",
      image: "",
      description: "",
      price: 0,
      quantity: 0
    };
  }

  componentDidMount() {
    const position = this.props.match.params.id;

    axios.get('http://localhost:8080/products/' + (position))
      .then((response) => {
        //this.setState(response.data);
        console.log("Gavau tokį produktą į redagavimą");
        console.log(this.state);
        console.log(response.data.id);
        console.log(response.data.title);
        this.setState({id: response.data.id});
        this.setState({title: response.data.title});
        this.setState({image: response.data.productDetails.image});
        this.setState({description: response.data.productDetails.description});
        this.setState({price: response.data.price});
        this.setState({quantity: response.data.quantity});
      
      console.log("Pagaminau tokį State ->" + this.state);
      console.log("Toks description iš state'o -> " + this.state.id);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  handleChangeOfTitle = (event) => {
    this.setState({ title: event.target.value });
    console.log(this.state.title);
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
    //const position = this.props.match.params.id;
    axios.put('http://localhost:8080/products/' + (this.state.id), this.state)

      .then(function (response) {
        /* axios.get('http://localhost:8080/products/' + (this.state.id))
                    .then((response) => {
                        this.setState({ products: response.data });
                    })
                    .catch((error) => {
                        console.log(error);
                    }); */
      })
      .catch(function (error) {
        console.log(error);
      });

  }

  render() {
    this.fromMenu = "Atnaujinamas produktas:";

    return (
      <EditProductComponent handleChangeOfTitle={this.handleChangeOfTitle}
        handleChangeOfImageUrl={this.handleChangeOfImageUrl}
        handleChangeOfDescription={this.handleChangeOfDescription}
        handleChangeOfPrice={this.handleChangeOfPrice}
        handleChangeOfQuantity={this.handleChangeOfQuantity}
        handleSubmit={this.handleSubmit}
        fromMenu={this.fromMenu}
        currentTitle={this.state.title}
        currentImage={this.state.image}
        currentDescription={this.state.description}
        currentPrice={this.state.price}
        currentQuantity={this.state.quantity}
      />
    );
  }
}

export default withRouter(EditProductContainer);