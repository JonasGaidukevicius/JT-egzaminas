import React from 'react';
//import PropTypes from 'prop-types';
import OneHolidayComponent from './OneHolidayComponent';
import axios from 'axios';

class OneHolidayContainer extends React.Component {
  constructor(props, context) {
    super(props, context);
    this.state = {
      code: "",
      title: "",
      image: "",
      description: '',
      type: '',
      flag: false,
      addedCountries: [],
      allCountries: [],
      countriesToAdd: [],
      countriesToRemove: []
    };
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! užkomentavau, nes man atrodo, kad čia aš eksperimentavau
    //this.userName = this.context;  
  }

  //  handleAddToCart = (userName) => {      
  //     console.log("--------------vartotojo vardas yra " + userName);
  //     axios.post('http://localhost:8080/api/users/' + userName + '/cart-products', this.state)

  //         .then((response) => {
  //             console.log("Sėkminga" + response);
  //         })
  //         .catch(function (error) {
  //             console.log(error);
  //         });
  // }

  // handleChangeOfQuantity = (event) => {
  //     this.setState({ quantity: event.target.value });
  //     console.log(this.state.quantity);
  //   }



  componentDidMount() {
    this.getOneHoliday();
    this.getHolidayCountryList();
    this.getCountryList();



  }

  getOneHoliday() {
    const position = this.props.match.params.code;
    axios.get('http://localhost:8080/api/holidays/' + (position))
      .then((response) => {
        //this.setState(response.data);
        console.log("-----------------Response data id yra: " + response.data.id);
        console.log("-----------------Response data title yra: " + response.data.title);
        this.setState({
          code: response.data.code,
          title: response.data.title,
          image: response.data.image,
          description: response.data.description,
          type: response.data.type,
          flag: response.data.flag,
          //addedCountries: response.data.countries
        })
      })
      .catch((error) => {
        console.log(error);
      });
  }

  getHolidayCountryList() {
    const position = this.props.match.params.code;
    axios.get('http://localhost:8080/api/holidays/' + position + '/addedCountries')
      .then((response) => {
        this.setState({ addedCountries: response.data })
      })
      .catch((error) => {
        console.log(error);
      });
  }

  getCountryList() {
    axios.get('http://localhost:8080/api/countries')
      .then((response) => {
        this.setState({ allCountries: response.data.map(item => item.title) })
      })
      .catch((error) => {
        console.log(error);
      });
  }

  availableCountrySelectionHandler = event => {
    this.setState({ countriesToAdd: [...event.target.selectedOptions].map(o => o.value) })
  }

  countryRemovingHandler = event => {
    this.setState({ countriesToRemove: [...event.target.selectedOptions].map(o => o.value) })
  }

  showAvailableCountries = () => {
    if (this.state.allCountries.length === 0) {
      return (
        <option value="" disabled>
          Nėra šalių pasirinkimui
            </option>
      );
    } else {
      let countries = this.state.allCountries
        .map((country, index) => {

          let isShown = true;

          this.state.addedCountries.forEach((c, index) => {
            if (c === country) {
              isShown = false;
            }
          });

          if (isShown)
            return (
              <option key={country + index} value={country}>
                {country}
              </option>
            );
          else {
            return null;
          }
        })
        .filter(c => c !== null);
      if (countries.length === 0) {
        return (
          <option value="" disabled>
            Visos šalys jau pasirinktos
              </option>
        );
      } else return countries;
    }
  };

  addCountriesToHoliday = event => {
    const position = this.props.match.params.code;
    axios.put('http://localhost:8080/api/holidays/' + position + '/addingCountries', this.state.countriesToAdd)
      .then(() => this.getHolidayCountryList())
      .catch(function (error) {
        console.log(error);
      });
  }

  removeCountriesFromHoliday = event => {
    const position = this.props.match.params.code;
    axios.put('http://localhost:8080/api/holidays/' + position + '/removingCountries', this.state.countriesToRemove)
      .then(() => this.getHolidayCountryList())
      .catch(function (error) {
        console.log(error);
      });
  }

  render() {
    return (
      <div>
        <OneHolidayComponent
          code={this.state.code}
          title={this.state.title}
          image={this.state.image}
          description={this.state.description}
          type={this.state.type}
          flag={this.state.flag === true ? "Yes" : "No"}
          addedCountries={this.state.addedCountries}
          allCountries={this.state.allCountries}
          showAvailableCountries={this.showAvailableCountries}
          availableCountrySelectionHandler={this.availableCountrySelectionHandler}
          addCountriesToHoliday={this.addCountriesToHoliday}
          removeCountriesFromHoliday={this.removeCountriesFromHoliday}
          countryRemovingHandler={this.countryRemovingHandler}
        />
        {/* <UserContext.Consumer>
                {
                    (userNameObject) => {
                       return <AddToCartComponent userName={userNameObject.user} handleAddToCart={this.handleAddToCart} handleChangeOfQuantity={this.handleChangeOfQuantity}/>
                    }
                } 
                </UserContext.Consumer>  */}
      </div>
    );
  }
}

export default OneHolidayContainer;