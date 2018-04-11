/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  View
} from 'react-native';
import { StackNavigator } from 'react-navigation';
import Login from './src/components/Login/Login';
import ProfileScreen from './src/components/ProfileScreen';
import Splash from './src/components/Splash'
import SearchScreen from './src/components/Search'
import CreateNew from './src/components/Login/CreateNew'
import MethodTest from './src/components/MethodTest'

const AppNavigator = StackNavigator (
    {

        Home: {screen: Login},
        Splash: {screen: Splash},
        Profile: {screen: ProfileScreen},
        Search: {screen: SearchScreen},
        CreateNew: {screen: CreateNew},
        Test: {screen: MethodTest},
    },
    {
      initialRouteName: 'Home',
    }
);

type Props = {};

export default class App extends Component<{}> {
  render() {
    return (
    <AppNavigator />
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
