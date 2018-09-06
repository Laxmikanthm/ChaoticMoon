using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using experitestClient;
namespace Experitest
{
    [TestClass]
    public class Untitled
    {
        private string host = "localhost";
        private int port = 8889;
        private string projectBaseDirectory = "C:\\ALL_ABOUT_SELENIUM_SEETEST\\PROJECTS\\ESPN\\ObjectManagement\\android";
        protected Client client = null;
        
        [TestInitialize()]
        public void SetupTest()
        {
            client = new Client(host, port, true);
            client.SetProjectBaseDirectory(projectBaseDirectory);
            client.SetReporter("xml", "reports", "Untitled");
        }

        [TestMethod]
        public void TestUntitled()
        {
            // it is recommended to start your script with SetDevice command:
            // client.SetDevice( <Device Name>);
            client.Launch("com.espn.fantasy.lm.football/com.espn.fantasy.lm.activity.MainActivity", true, false);
        }

        [TestCleanup()]
        public void TearDown()
        {
            client.GenerateReport(true);
        }
    }
}