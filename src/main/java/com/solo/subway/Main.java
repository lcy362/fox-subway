package com.solo.subway;

import com.solo.subway.data.SubwayInfoParser;
import com.solo.subway.path.Dijkstra;
import com.solo.subway.util.Station;
import com.solo.subway.util.SubwayLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String args[]) throws IOException {
        Logger logger = LoggerFactory.getLogger(Main.class);
        SubwayInfoParser subway = SubwayInfoParser.getInstace();
        subway.parse();
        Map<String, Station> stations = subway.getStations();
        Map<String, SubwayLine> lineName = subway.getLineName();

        for (Station station : stations.values()) {
            logger.info(station.getName());
            for (String line : station.getLines()) {
                logger.info(station.getName() + " in " + lineName.get(line).getName());
            }
            for (String next : station.getNextStations()) {
                logger.info(station.getName() + " next to " + stations.get(next).getName());
            }
        }

        Dijkstra.pathToAll("奥林匹克公园", stations);

    }
}
