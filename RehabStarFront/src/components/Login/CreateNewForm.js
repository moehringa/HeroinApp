import React, {Component} from 'react';
import { StyleSheet, View, TextInput, TouchableOpacity, Text, StatusBar,
KeyboardAvoidingView } from 'react-native';
import { StackNavigator } from 'react-navigation';
import Login from './Login';
import ProfileScreen from '../ProfileScreen';
import Splash from '../Splash'


export default class LoginForm extends React.Component {

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
            onSubitEditing={() => this.passowrdInput.focus()}
            keyboardType = 'email-address'
            autoCapitalize='none'
            autoCorrect={false}
            underlineColorAndroid='rgba(0,0,0,0)'
            style = {styles.input}
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
            />

            <TouchableOpacity style = {styles.buttonContainer}
              onPress = {()=> this.props.navigation.navigate('Home')}>
              <Text style = {styles.buttonText}>SUBMIT</Text>
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
