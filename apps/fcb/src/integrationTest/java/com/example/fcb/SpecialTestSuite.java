package com.example.fcb;


import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@SelectPackages("com.example.fcb")
@IncludeTags("uat")
@Suite
public class SpecialTestSuite {
    // ./gradlew check
    // run test
    // run integrationTest

}
