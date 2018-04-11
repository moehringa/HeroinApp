import React, { Component, Button } from 'react';
import { StyleSheet, View, Image, Text, KeyboardAvoidingView, TouchableOpacity} from 'react-native';
import LoginForm from './LoginForm';


export default class Login extends React.Component {
  static navigationOptions = {
    title: 'Login',
    header: null,
  };


  render() {
    return (
      <View style={styles.container}>
        <View style={styles.logoContainer}>
          <Image
          style={styles.logo}
          source={require('../../images/logo.jpg')}
          />

          <Text style={styles.title}>Add Some App Description</Text>
        </View>
        <View style={styles.formContainer}>
        <LoginForm navigation={this.props.navigation}/>
        </View>
        <View style={styles.bottomCont}>
        <Text style={styles.accText}>
          Need A new account?
        </Text>
        <TouchableOpacity
          onPress = {()=> this.props.navigation.navigate('CreateNew')}>
          <Text style = {styles.newNew}>Create Account.</Text>
        </TouchableOpacity>
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#30C5FF'
  },
  logoContainer: {
    alignItems: 'center',
    flexGrow: 1,
    justifyContent: 'center',
  },
  logo: {
    width: 100,
    height: 100,
  },
  title: {
    color: 'white',
    marginTop: 10,
    width: 160,
    textAlign: 'center',
    opacity: 0.9,
  },
  formContainer: {
    marginBottom: 25
  },
  newNew: {
    flexGrow: 1,
    color: 'white',
    justifyContent: 'flex-end',
    textAlign: 'center'
  },
  accText: {
    textAlign: 'center',
  },
  bottomCont: {
    flexGrow: 1,
  },
})
