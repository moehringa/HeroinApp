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

        <View style = {styles.id}>
          <Text> {this.state.username} </Text>
          <Text> ID: {this.state.id} </Text>
          <Text> Password: {this.state.password} </Text>
        </View>

        <View style={styles.email}>
          <Text> Email: {this.state.email} </Text>
        </View>

        <View style = {styles.goal}>

          <View style = {styles.days}>
            <Text> Days Clean: {this.state.daysClean} </Text>
          </View>
          <View style = {styles.days}>
            <Text> Goal: {this.state.goalDaysClean} days </Text>
          </View>

        </View>

          <Text> Search Field: {this.state.currentSearch} </Text>

        <View style = {styles.bottomButtons}>
          <TouchableOpacity style = {styles.buttonContainer}
            onPress = {this.goToStories}>
            <Text style = {styles.buttonText}>See the Stories</Text>
          </TouchableOpacity>
        </View>
      </View>

    );
  }
}

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
    width: 100,
    height: 50,
  },
  buttonText: {
    textAlign: 'center',
    color: '#FFFFFF',
    fontWeight: 'bold'
  },
  id: {
    flex: 1.5,
    backgroundColor: '#30C5FF',
    alignItems: 'center',
    justifyContent: 'flex-end',
    padding: 15,
  },
  email: {
    flex: 1,
    backgroundColor: '#52FFB8',
    alignItems: 'center',
    justifyContent: 'center',
  },
  days: {
    flex: 1,
    backgroundColor: '#539987',
    alignItems: 'center',
    justifyContent: 'center',
  },
  goal: {
    flex: 1,
    flexDirection: 'row',
    backgroundColor: 'yellow',
  },
  bottomButtons: {
    flex: .3,
    alignItems: 'center',
    flexDirection: 'row',
    justifyContent: 'center',
  }
});
