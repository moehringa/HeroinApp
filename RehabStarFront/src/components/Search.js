import React, {Component} from 'react';
import { Button, StyleSheet, View, TextInput, TouchableOpacity, Text, StatusBar } from 'react-native';
//import { Container, Header, Tab, Tabs, TabHeading, Icon, Text } from 'native-base';
import StoryFeed from './StoryFeed'

export default class SearchScreen extends React.Component {
  static navigationOptions = {
    title: 'Profile',
  };

  render() {

    return (
      <View style={styles.container}>
        <Text> This is the Search Screen </Text>
        <StoryFeed/>


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
