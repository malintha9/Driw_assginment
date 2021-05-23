import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import AppNavbar from './AppNavbar';
import ProdcutList from './ProductList.js';
import Calculate from './Calculate.js';

class App extends Component{
 
  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Home}/>
          <Route path='/products' exact={true} component={ProdcutList}/>
          <Route path='/calculate' component={Calculate}/>
        </Switch>
      </Router>
    )
  }

}


export default App;
