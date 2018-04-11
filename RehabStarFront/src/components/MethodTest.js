import React, {Component} from 'react';
import { Button, StyleSheet, View, TextInput, TouchableOpacity, Text, StatusBar,
          ToolbarAndroid,} from 'react-native';
import { StackNavigator } from 'react-navigation';

export default class ProfileScreen extends React.Component {
  static navigationOptions = {
    title: 'Test',
  };
  constructor(props){
    super(props);
    this.state={
      'users': [],
      error: false,
    }
  }

  render() {

    return (
      <View style={styles.container}>
        <Text> {this.state.users} </Text>
      </View>
    );
  }
}


const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
    formContainer: {
      flexGrow: 1,
      justifyContent: 'center',
      alignItems: 'center',
      marginBottom: 50,
    },
    navbar: {
      flexDirection: 'row',
      flex: 4,
    },
});
