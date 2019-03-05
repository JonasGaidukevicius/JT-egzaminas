import React from 'react';
import NewHolidayComponent from './NewHolidayComponent';
import axios from 'axios';
import { withRouter } from 'react-router';


class NewHolidayContainer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      title: '',
      description: '',
      image: '',
      type: '',
      flag: false,
     
    };
    //var fromMenu;
  }

  handleChangeOfTitle = (event) => {
    this.setState({ title: event.target.value });
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
    //console.log("Kokia vėliavos reikšmė? -> " + event.target.checked);
    this.setState({ flag: event.target.checked });
  }

  handleSubmit = (event) => {
    event.preventDefault();
    console.log(this.state);
    axios.post('http://localhost:8080/holidays', this.state)
      .then(response => this.props.history.push(`/admin`)) //reikia nurodyti kelią, kur turėtų atsirasti
      .catch(function (error) {
        console.log(error);
      });
  }

  componentDidMount() {

    //console.log(this.props.history.location.pathname);
  }


  render() {
    
    this.fromMenu = "Enter new holiday data:"

    return (
      <NewHolidayComponent
        title={this.state.title}
        description={this.state.description}
        image={this.state.image}
        type={this.state.type}
        flag={this.state.flag}
        handleChangeOfTitle={this.handleChangeOfTitle}
        handleChangeOfImage={this.handleChangeOfImage}
        handleChangeOfDescription={this.handleChangeOfDescription}
        handleChangeOfType={this.handleChangeOfType}
        handleChangeOfFlag={this.handleChangeOfFlag}
        handleSubmit={this.handleSubmit}
        //TO DO Ar reikia šitą perdavinėti?
        fromMenu={this.fromMenu}
      />
    );
  }
}

//TO DO Perdaryti, kad būtų be Rūterio
export default withRouter(NewHolidayContainer);