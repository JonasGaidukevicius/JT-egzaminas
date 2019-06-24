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
      typeList: [],
      flag: false,
      simpleDate: ''
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

  handleChangeOfSimpleDate = (event) => {
    //console.log("Kokia vėliavos reikšmė? -> " + event.target.checked);
    this.setState({ simpleDate: event.target.value });
  }

  handleSubmit = (event) => {
    event.preventDefault();
    console.log(this.state);
    axios.post('http://localhost:8080/api/holidays', this.state)
      .then(response => {
              console.log(response);
              this.props.history.push(`/admin/holidays`)}) //reikia nurodyti kelią, kur turėtų atsirasti
      .catch((error, response) => {
        console.log(response);
        console.log(error.response.data.message);
        alert(error.response.data.message);
      });
  }

  componentDidMount() {
    //Kai Springas gali atiduoti tipų sąrašą, tada galima jį nuskaityti ir panaudoti
    //Čia nuskaito tipus iš serverio ir sudeda juos į typeList
    // axios
    //   .get("http://localhost:8081/api/users/typesList")
    //   .then(response => {
    //     this.setState({ typeList: response.data.map(item => item.title) });
    //   })
    //   .catch(error => {
    //     console.log(error);
    //   }); 
  }


  render() {
    
    this.fromMenu = "Enter new holiday data:"

    return (
        <NewHolidayComponent
          title={this.state.title}
          description={this.state.description}
          image={this.state.image}
          type={this.state.type}
          typeListt={this.state.typeList}
          flag={this.state.flag}
          simpleDate={this.state.simpleDate}
          handleChangeOfTitle={this.handleChangeOfTitle}
          handleChangeOfImage={this.handleChangeOfImage}
          handleChangeOfDescription={this.handleChangeOfDescription}
          handleChangeOfType={this.handleChangeOfType}
          handleChangeOfFlag={this.handleChangeOfFlag}
          handleChangeOfSimpleDate={this.handleChangeOfSimpleDate}
          handleSubmit={this.handleSubmit}
          //TO DO Ar reikia šitą perdavinėti?
          fromMenu={this.fromMenu}
        />
    );
  }
}

//TO DO Perdaryti, kad būtų be Rūterio
export default withRouter(NewHolidayContainer);