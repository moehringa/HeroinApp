import React, {Component} from 'react';
import { StyleSheet, View, TextInput, TouchableOpacity, Text, StatusBar,
KeyboardAvoidingView, Image } from 'react-native';
import { StackNavigator } from 'react-navigation';
import Login from './Login';
import ProfileScreen from '../ProfileScreen';
import {getUserInfo, getAllUsers, authenticateUser} from '../../services/MobileService'


export default class LoginForm extends React.Component {
  constructor(props){
    super(props);
    this.state={
      username: 'dmterk',
      password: '',
      auth: false,
      error: false,
    }
    this.handleSubmit = this.handleSubmit.bind(this);
    //this.handleAuth = this.handleAuth.bind(this);
  }

  handleSubmit = () => {
    getUserInfo(this.state.username)
    .then((res) => {
      if(res.statusText == 'Not Found') {
        this.setState({
          error: 'User not found'
        });
      }
      else {
        //let userInfo = {res.json()}
        this.props.navigation.navigate(
          'Profile',
          {userInfo: res}
        );
        this.setState({
          error: false,
          username: ''
        })
      }
    });
  }

  // handleAuth = () => {
  //   authenticateUser(this.state.name, this.state.password)
  //     .then((res) => {
  //       this.setState({auth: JSON.stringify(res)});
  //       if(this.state.auth == true){
  //         this.props.navigation.navigate(
  //           'Profile',
  //           {userInfo: getUserInfo(this.state.username)}
  //         );
  //         this.setState(
  //           {error: false,
  //             username:'',
  //             auth: false
  //           })
  //       }
  //     });
  // }

    render() {
        return (
          <KeyboardAvoidingView behavior='padding' style={styles.container}>
          <StatusBar
            barStyle='light-content'
            />
            
            <TextInput
            placeholder = 'username'
            placeholderTextColor = 'rgba(255,255,255,0.7)'
            returnKeyType='next'
            onSubmitEditing={() => this.password.focus()}
            keyboardType = 'email-address'
            autoCapitalize='none'
            autoCorrect={false}
            underlineColorAndroid='rgba(0,0,0,0)'
            style = {styles.input}
            onChangeText={(username) => this.setState({username: username})}
            />
            <TextInput
            placeholder = 'password'
            placeholderTextColor = 'rgba(255,255,255,0.7)'
            returnKeyType='go'
            ref = {(input) => this.password = input}
            //onSubmitEditing={}
            autoCapitalize='none'
            autoCorrect={false}
            underlineColorAndroid='rgba(0,0,0,0)'
            secureTextEntry
            style = {styles.input}
            onChangeText={(password) => this.setState({password: password})}
            />

            <TouchableOpacity style = {styles.buttonContainer}
              onPress = {this.handleSubmit}>
              <Text style = {styles.buttonText}>LOGIN</Text>
            </TouchableOpacity>
          </KeyboardAvoidingView>
        );
    }
}
const styles = StyleSheet.create({
  container: {
    padding: 20,
  },
  input: {
    height: 40,
    backgroundColor: 'rgba(255,255,255,0.2)',
    marginBottom: 10,
    color: '#FFF',
    paddingHorizontal: 10,
  },
  buttonContainer: {
    backgroundColor: 'gray',
    paddingVertical: 15,
    marginBottom: 20,
  },
  buttonText: {
    textAlign: 'center',
    color: '#FFFFFF',
    fontWeight: 'bold'
  },
});
