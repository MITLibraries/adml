package edu.mit.adml.properties;

import edu.mit.adml.DatabaseInitializer;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO use Commons Util or Spring Properties
public class PropertiesConfigurationUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesConfigurationUtil.class);

    /**
     * For getting login details
     */
    private static Configuration login;

    static {

        try {

            final Parameters params = new Parameters();
            FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                            .configure(params.properties()
                                    .setFileName("aspace.properties"));

            login = builder.getConfiguration();

            //TODO merge connection.properties with application-prod.properties
        } catch (Exception e) {
            logger.error("Error setting property file:", e);
        }
    }

    public static Credentials getCredentials() {
        final Credentials credentials = new Credentials();

        if (login != null) {
            credentials.setPassword(login.getProperty("login_password").toString());
            credentials.setUrl(login.getProperty("login_url").toString());
        } else {
            logger.info("No information provided for ArchivesSpace. Provide aspace.properties.");
        }

        return credentials;
    }
}