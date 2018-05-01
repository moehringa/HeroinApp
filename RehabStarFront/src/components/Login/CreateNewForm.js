import React, {Component} from 'react';
import { StyleSheet, View, TextInput, TouchableOpacity, Text, StatusBar,
KeyboardAvoidingView } from 'react-native';
import { newUser } from '../../services/MobileService'

export default class LoginForm extends React.Component {

  constructor(props){
    super(props);
    this.state={
      username: '',
      password: '',
      email: '',
      auth: false,
      error: false,
    }
    this.handleSubmit = this.handleSubmit.bind(this);
    this.defaultUsername = this.defaultUsername.bind(this);
  }

  handleSubmit = () => {
    let email = this.state.email;
    let strings = email.split("@");
    let user = strings[0];
    this.setState({username: user});

    newUser(this.state.username, this.state.email, this.state.password);
    this.props.navigation.navigate('Home');
  }

  defaultUsername = (stringy) => {
    let newName = stringy.split("@");
    this.setState({username: newName[0]});
  }

    render() {


        return (

          <KeyboardAvoidingView behavior='padding' style={styles.container}>
          <View>
          <Text>{this.state.username},{this.state.email},{this.state.password}</Text>
          </View>
          <StatusBar
            barStyle='light-content'
            />
            <TextInput
            placeholder = 'email'
            placeholderTextColor = 'rgba(255,255,255,0.7)'
            returnKeyType='next'
            onSubitEditing={() => this.passoword.focus()}
            keyboardType = 'email-address'
            autoCapitalize='none'
            autoCorrect={false}
            underlineColorAndroid='rgba(0,0,0,0)'
            style = {styles.input}
            onChangeText={(email) => this.setState({email: email})}
            />
            <TextInput
            ref = {(input) => this.password = input}
            placeholder = 'password'
            placeholderTextColor = 'rgba(255,255,255,0.7)'
            returnKeyType='go'
            //onSubitEditing={}
            autoCapitalize='none'
            autoCorrect={false}
            underlineColorAndroid='rgba(0,0,0,0)'
            secureTextEntry
            style = {styles.input}
            onChangeText={(password) => this.setState({password: password})}
            />

            <TouchableOpacity style = {styles.buttonContainer}
              onPress = {this.handleSubmit}>
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
