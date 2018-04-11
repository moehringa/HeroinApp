import React, {Component} from 'react';
import { Button, StyleSheet, View, TextInput, TouchableOpacity, Text, StatusBar,
          ToolbarAndroid,} from 'react-native';
import { StackNavigator } from 'react-navigation';

export default class ProfileScreen extends React.Component {
  static navigationOptions = {
    title: 'Profile',
  };
  constructor(props){
    super(props);
    this.state={
      'id':this.props.navigation.state.params.userInfo.id,
      'username':this.props.navigation.state.params.userInfo.userName,
      'email':this.props.navigation.state.params.userInfo.email,
      'password':this.props.navigation.state.params.userInfo.password,
      'daysClean':this.props.navigation.state.params.userInfo.daysClean,
      'goalDaysClean': this.props.navigation.state.params.userInfo.goalDaysClean,
      'currentSearch': this.props.navigation.state.params.userInfo.currentSearch,
      error: false,
    }
  }

  render() {

    return (
      <View style={styles.container}>
        <Text> {this.state.username} </Text>
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
