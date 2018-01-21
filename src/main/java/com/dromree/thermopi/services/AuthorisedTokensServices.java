package com.dromree.thermopi.services;

import com.dromree.thermopi.dbaccess.data.authorisedtokens.AuthorisedToken;
import com.dromree.thermopi.dbaccess.mongodb.repositories.mongodb.AuthorisedTokensRepository;
import com.dromree.thermopi.rest.data.AuthorisedTokenData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorisedTokensServices {

    private AuthorisedTokensRepository authorisedTokensRepository;

    private AuthorisedTokenData convertDBToNetworkData(AuthorisedToken dbData) {
        AuthorisedTokenData networkData = null;
        if(dbData != null) {
            networkData = new AuthorisedTokenData(dbData.getUser(), dbData.getToken());
        }

        return networkData;
    }

    private List<AuthorisedTokenData> convertDBToNetworkDataList(List<AuthorisedToken> dbList) {
        List<AuthorisedTokenData> networkList = new ArrayList<>();

        dbList.forEach((entry) -> networkList.add(convertDBToNetworkData(entry)));

        return networkList;
    }

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

    public AuthorisedTokenData getAuthorisedTokenByToken(String token) {
        AuthorisedTokenData returnData = null;

        AuthorisedToken dbToken = authorisedTokensRepository.findFirstByToken(token);

        if(dbToken != null) {
            returnData = convertDBToNetworkData(dbToken);
        }

        return returnData;
    }

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
