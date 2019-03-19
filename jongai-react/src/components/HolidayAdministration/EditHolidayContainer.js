import React from 'react';
import EditHolidayComponent from './EditHolidayComponent';
import axios from 'axios';
import { withRouter } from 'react-router';

class EditHolidayContainer extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      oldTitle: "",
      code: "",
      title: "",
      image: "",
      description: "",
      type: "",
      flag: false
    };
  }

  componentDidMount() {
    const position = this.props.match.params.code;
    console.log("Gaunu tokį position - " + position);

    axios.get('http://localhost:8080/holidays/' + (position))
      .then((response) => {
        //this.setState(response.data);
        console.log("Gavau tokį produktą į redagavimą");
        console.log(this.state);
        // console.log(response.data.id);
        //console.log(response.data.title);
        this.setState({ oldTitle: response.data.title });
        this.setState({ code: response.data.code});
        this.setState({ title: response.data.title });
        this.setState({ image: response.data.image });
        this.setState({ description: response.data.description });
        this.setState({ type: response.data.type });
        this.setState({ flag: response.data.flag });

        console.log("Pagaminau tokį State ->" + this.state);
        //console.log("Toks description iš state'o -> " + this.state.id);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  handleChangeOfTitle = (event) => {
    this.setState({ title: event.target.value });
    console.log(this.state.title);
  }

  handleChangeOfImage = (event) => {
    this.setState({ image: event.target.value });
  }

  handleChangeOfDescription = (event) => {
    this.setState({ description: event.target.value });
  }

  handleChangeOfType = (event) => {
    this.setState({ type: event.target.value });
  }

  handleChangeOfFlag = (event) => {
    this.setState({ flag: event.target.checked });
  }

  handleSubmit = (event) => {
    event.preventDefault();
    //const position = this.props.match.params.id;
    axios.put('http://localhost:8080/holidays/' + (this.state.code), this.state)

      .then(response => this.props.history.push(`/admin`))
      .catch(function (error) {
        console.log(error);
      });

  }

  handleDelete = (event) => {
    event.preventDefault();
    console.log("Noriu ištrinti " + this.state.code);
    axios.delete('http://localhost:8080/holidays/' + (this.state.code))
      .then(response => this.props.history.push(`/admin`))
      .catch(function (error) {
        console.log(error);
      });
  }

  render() {
    this.fromMenu = "Atnaujinama Šventė:";

    return (
      <EditHolidayComponent
        currentCode={this.state.code}
        currentTitle={this.state.title}
        currentImage={this.state.image}
        currentDescription={this.state.description}
        currentType={this.state.type}
        currentFlag={this.state.flag}
        handleChangeOfTitle={this.handleChangeOfTitle}
        handleChangeOfImage={this.handleChangeOfImage}
        handleChangeOfDescription={this.handleChangeOfDescription}
        handleChangeOfType={this.handleChangeOfType}
        handleChangeOfFlag={this.handleChangeOfFlag}
        handleSubmit={this.handleSubmit}
        handleDelete={this.handleDelete}
        fromMenu={this.fromMenu}

      />
    );
  }
}

export default withRouter(EditHolidayContainer);