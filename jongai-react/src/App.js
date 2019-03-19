import React, { Component } from 'react';
import './App.css';

import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import { Switch, Redirect, Route } from 'react-router';
import { BrowserRouter, Link } from 'react-router-dom';
import NoMatch from './components/Navigation/NoMatch';
import NavigationContainer from './components/Navigation/NavigationContainer';
import HolidayListContainer from './components/HolidayList/HolidayListContainer';
import CountryAdministrationContainer from './components/CountryAdministration/CountryAdministrationListContainer';
import NewHolidayContainer from './components/HolidayAdministration/NewHolidayContainer';
import UserContext from './UserContext';
import HolidayAdministrationListContainer from './components/HolidayAdministration/HolidayAdministrationListContainer';
import EditHolidayContainer from './components/HolidayAdministration/EditHolidayContainer';
import OneHolidayContainer from './components/HolidayList/OneHolidayContainer';
import NewCountryContainer from './components/CountryAdministration/NewCountryContainer';
import EditCountryContainer from './components//CountryAdministration/EditCountryContainer';
import Pasimokyti from './Pasimokyti';


class App extends Component {

  DemonstruotiNavigacija = (props) => {
    var goHome = () => props.history.push("/");
    return (
      <div>
        At route: {props.location.pathname}
        <button onClick={goHome}>Go Home</button>
        <pre>
          {JSON.stringify(props, null, 1)}
        </pre>
      </div>
    );
  };


  render() {
    return (
      <BrowserRouter>
        <UserContext.Provider value={{ user: "Jonas" }}>
          <NavigationContainer>
            <Switch>
              <Route exact path='/' component={HolidayListContainer} />
              <Route exact path="/holidays/:code" component={OneHolidayContainer} />
              <Route exact path='/admin' component={HolidayAdministrationListContainer} />
              <Route exact path='/admin/country' component={CountryAdministrationContainer} />
              {/* <Route exact path='/pasimokyti' component={Pasimokyti} />           */}
              <Route exact path="/admin/holidays/new" component={NewHolidayContainer} />
              <Route exact path="/admin/holidays/:code" component={EditHolidayContainer} />
              <Route exact path="/admin/countries/new" component={NewCountryContainer} />
              <Route exact path="/admin/countries/:title" component={EditCountryContainer} />
              {/* <Route exact path="/shopping-Cart/:user" component={ShoppingCartContainer} />                                */}
              <Route path="*" component={NoMatch} />
              <Route component={NoMatch} />
            </Switch>
          </NavigationContainer>
        </UserContext.Provider>
      </BrowserRouter>
    );
  }
}

export default App;
