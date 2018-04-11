import React, {Component} from 'react';
import { StyleSheet, View, TextInput, TouchableOpacity, Text, StatusBar,
KeyboardAvoidingView } from 'react-native';
import { StackNavigator } from 'react-navigation';
import Login from './Login';
import ProfileScreen from '../ProfileScreen';
import Splash from '../Splash'
import {getUserInfo, getAllUsers} from '../../services/MobileService'


export default class LoginForm extends React.Component {
  constructor(props){
    super(props);
    this.state={
      username: 'dmterk',
      password: '',
      error: false,
    }
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleSUbmit2 = this.handleSubmit2.bind(this);
  }

  handleSubmit = () => {
    getUserInfo(this.state.username)
    .then((res) => {
      if(res.message === 'Not Found') {
        this.setState({
          error: 'User not found'
        });
      }
      else {
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

  handleSubmit2 = () => {
    getAllUsers()
    .then((res) => {
      if(res.message === 'Not Found') {
        this.setState({
          error: 'User not found'
        });
      }
      else {
        this.props.navigation.navigate(
          'Test',
          {users: res}
        );
        this.setState({
          error: false,
          username: ''
        })
      }
    });
  }

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
            onSubmitEditing={() => this.passowrdInput.focus()}
            keyboardType = 'email-address'
            autoCapitalize='none'
            autoCorrect={false}
            underlineColorAndroid='rgba(0,0,0,0)'
            style = {styles.input}
            onChangeText={(username) => this.setState({username})}
            />
            <TextInput
            placeholder = 'password'
            placeholderTextColor = 'rgba(255,255,255,0.7)'
            returnKeyType='go'
            //onSubitEditing={}
            autoCapitalize='none'
            autoCorrect={false}
            underlineColorAndroid='rgba(0,0,0,0)'
            secureTextEntry
            style = {styles.input}
            onChangeText={(password) => this.setState({password})}
            />

            <TouchableOpacity style = {styles.buttonContainer}
              onPress = {this.handleSubmit2}>
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
