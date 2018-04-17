 import React, {Component} from 'react';
import { Button, StyleSheet, View, TextInput, TouchableOpacity, Text, StatusBar,
  ToolbarAndroid,} from 'react-native';
import { StackNavigator, TabNavigator} from 'react-navigation';
import { getAllStories } from '../services/MobileService'

export default class ProfileScreen extends React.Component {
  static navigationOptions = {
    title: 'Profile',
  };

  constructor(props){
    super(props);
    let user = this.props.navigation.state.params.userInfo;
    this.state={
      id: this.props.navigation.state.params.userInfo.id,
      username: this.props.navigation.state.params.userInfo.userName,
      email: this.props.navigation.state.params.userInfo.email,
      password: this.props.navigation.state.params.userInfo.password,
      daysClean: this.props.navigation.state.params.userInfo.daysClean,
      goalDaysClean: this.props.navigation.state.params.userInfo.goalDaysClean,
      currentSearch: this.props.navigation.state.params.userInfo.currentSearch,
      error: false,
    }
    this.goToStories = this.goToStories.bind(this);
  }

  goToStories = () => {
    getAllStories()
    .then((res) => {
      if(res.statusText == 'Not Found') {
        this.setState({
          error: 'User not found'
        });
      }
      else {
        this.props.navigation.navigate(
          'StoryFeed',
          {stories: res}
        );
        this.setState({
          error: false,
        })
      }
    });
  }

  render() {

    return (
      <View style={styles.container}>
      <Text> ID: {this.state.id} </Text>
      <Text> username: {this.state.username} </Text>
      <Text> Email: {this.state.email} </Text>
      <Text> Password: {this.state.password} </Text>
      <Text> Days Clean: {this.state.daysClean} </Text>
      <Text> Goal: {this.state.goalDaysClean} days </Text>
      <Text> Search Field: {this.state.currentSearch} </Text>

      <TouchableOpacity style = {styles.buttonContainer}
        onPress = {this.goToStories}>
      <Text style = {styles.buttonText}>See the Stories</Text>
      </TouchableOpacity>
      </View>

    );
  }
}

// const TabNav = TabNavigator({
//   TabItem1: {
//     screen: ProfileScreen,
//     navigationOptions: {
//       tabBarLabel:"Profile",
//       tabBarIcon: ({ tintColor }) => <Icon name={"glass"} size={30} color={tintColor} />
//     }
//   },
//   ///... add more tabs here
//
// }, {
//   tabBarOptions: {
//     activeTintColor: '#222',
//   }
// });

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#30C5FF',
  },
  formContainer: {
    flexGrow: 1,
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: 50,
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
