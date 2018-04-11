import React, { Component } from 'react';
import { View, Text, StyleSheet } from 'react-native';

export default class Splash extends Component {
  render() {
    return (
      <View style={styles.container}>
        <View style = {styles.titleWrapper}>
          <Text style = {styles.title}>
          UpLift App
          </Text>
        </View>
        <View>
          <Text style = {styles.subtitle}>
          It was built with react native
          </Text>
        </View>
      </View>
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
  title: {
    color: 'black',
    fontSize: 35,
    fontWeight: 'bold',
    },
  subtitle: {
    color: 'black',
    fontWeight: 'normal',
    padding: 20,
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
  titleWrapper: {
    justifyContent: 'center',
    flex: 1,
  },
});
