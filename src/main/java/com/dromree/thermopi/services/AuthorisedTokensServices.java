package com.dromree.thermopi.services;

import com.dromree.thermopi.dbaccess.data.authorisedtokens.AuthorisedToken;
import com.dromree.thermopi.dbaccess.mongodb.repositories.mongodb.AuthorisedTokensRepository;
import com.dromree.thermopi.rest.data.AuthorisedTokenData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Services for Authorised Tokens
 *
 */
@Service
public class AuthorisedTokensServices {

    private AuthorisedTokensRepository authorisedTokensRepository;

    /**
     * Converts a db AuthorisedToken to a network AuthorisedToken
     *
     * @param dbData    AuthorisedToken to be converted
     * @return          network AuthorisedToken
     */
    private AuthorisedTokenData convertDBToNetworkData(AuthorisedToken dbData) {
        AuthorisedTokenData networkData = null;
        if(dbData != null) {
            networkData = new AuthorisedTokenData(dbData.getUser(), dbData.getToken());
        }

        return networkData;
    }

    /**
     * Converts a list of db AuthorisedTokens to a list of network AuthorisedTokens
     *
     * @param dbList    list of AuthorisedTokens to be converted
     * @return          List of network AuthorisedTokens
     */
    private List<AuthorisedTokenData> convertDBToNetworkDataList(List<AuthorisedToken> dbList) {
        List<AuthorisedTokenData> networkList = new ArrayList<>();

        dbList.forEach((entry) -> networkList.add(convertDBToNetworkData(entry)));

        return networkList;
    }

    /**
     * Converts a network AuthorisedToken to a db AuthorisedToken
     *
     * @param networkData   Authorised token to be converted
     * @return              db AuthorisedToken
     */
    private AuthorisedToken convertNetworkToDBData(AuthorisedTokenData networkData) {
        AuthorisedToken dbData = null;
        if(networkData != null) {
            dbData = new AuthorisedToken(networkData.getUser(), networkData.getToken());
        }

        return dbData;
    }

    @Autowired
    public AuthorisedTokensServices(AuthorisedTokensRepository authorisedTokensRepository) {
        this.authorisedTokensRepository = authorisedTokensRepository;
    }

    /**
     * Gets a list of all Authorised Tokens in the database
     *
     * @return  List of all Authorised Tokens
     */
    public List<AuthorisedTokenData> getAllTokens() {
        List<AuthorisedTokenData> returnData;

        List<AuthorisedToken> dbTokens = authorisedTokensRepository.findAll();

        if(dbTokens.size() > 0) {
            returnData = convertDBToNetworkDataList(dbTokens);
        } else {
            returnData = new ArrayList<>();
        }

        return returnData;
    }

    /**
     * Retrieves an Authorised Token from the database by the token value
     *
     * @param token Token value to search by
     * @return      returns the token if found, null otherwise
     */
    public AuthorisedTokenData getAuthorisedTokenByToken(String token) {
        AuthorisedTokenData returnData = null;

        AuthorisedToken dbToken = authorisedTokensRepository.findFirstByToken(token);

        if(dbToken != null) {
            returnData = convertDBToNetworkData(dbToken);
        }

        return returnData;
    }

    /**
     * Adds the token provided to the database
     *
     * @param tokenData token to be added to the database
     */
    public void addTokenForUser(AuthorisedTokenData tokenData) {
        AuthorisedToken authorisedToken = authorisedTokensRepository.findFirstByUser(tokenData.getUser());

        if(authorisedToken != null) {
            authorisedToken.setToken(tokenData.getToken());
        } else {
            authorisedToken = convertNetworkToDBData(tokenData);
        }

        authorisedTokensRepository.save(authorisedToken);
    }
}
