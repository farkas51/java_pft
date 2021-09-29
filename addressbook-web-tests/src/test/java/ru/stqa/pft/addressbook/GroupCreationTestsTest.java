package ru.stqa.pft.addressbook;
import org.testng.annotations.Test;

public class GroupCreationTestsTest extends TestBase {

  @Test
  public void groupCreationTests() {
    goToGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test1", "test2", "test3"));
    submitGroupCreation();
  }

}
